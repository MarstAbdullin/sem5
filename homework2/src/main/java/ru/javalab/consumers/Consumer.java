package ru.javalab.consumers;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import com.itextpdf.styledxmlparser.css.util.CssUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.javalab.data.Keys;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public abstract class Consumer implements Runnable {

    public static final String SRC = "src/main/resources/templates/";
    public static final String DEST = "generated/";

    @Override
    public void run() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Keys.HOST_NAME);

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(1);

            channel.exchangeDeclare(exchangeName(), exchangeType());
            channel.queueDeclare(queueName(), false, false, false, null);
            channel.queueBind(queueName(), exchangeName(), exchangeRoutingKey());

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    generate(new String(message.getBody()));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(queueName(), false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected abstract String getFilename();

    protected abstract String exchangeName();

    protected abstract String exchangeType();

    protected abstract String exchangeRoutingKey();

    protected abstract String queueName();

    protected abstract String templateName();

    protected String data(String message) {
        return message;
    }

    private void generate(String message) throws IOException {
        File file = new File(DEST);
        file.mkdirs();
        String htmlSource = SRC + templateName();

        float width = CssUtils.parseAbsoluteLength(Float.toString(PageSize.A4.getWidth()));
        String dest = DEST + getFilename();

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdfDoc = new PdfDocument(writer);

        pdfDoc.setTagged();
        pdfDoc.setDefaultPageSize(PageSize.A4);

        ConverterProperties converterProperties = new ConverterProperties();

        MediaDeviceDescription mediaDescription = new MediaDeviceDescription(MediaType.SCREEN);
        mediaDescription.setWidth(PageSize.A4.getWidth());
        converterProperties.setMediaDeviceDescription(mediaDescription);

        converterProperties.setBaseUri(SRC);

        converterProperties.setCreateAcroForm(true);


        String html = Files.lines(Paths.get(htmlSource)).collect(Collectors.joining());

        html = html.replace("${exchangeName}", exchangeName());
        html = html.replace("${routingKey}", exchangeRoutingKey());
        html = html.replace("${data}", data(message));

        HtmlConverter.convertToPdf(new ByteArrayInputStream(html.getBytes()), pdfDoc, converterProperties);

        pdfDoc.close();
    }

}
