package ru.rybinskov.ideas4transfer.service.excel_report_service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import ru.rybinskov.ideas4transfer.dto.DeliveryDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;

public class ExcelReportView extends AbstractXlsView {

    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) {

        response.setHeader("Content-Disposition", "attachment;filename=\"report.xls\"");
        List<DeliveryDto> deliveryDtos = (List<DeliveryDto>) model.get("reportDeliveries");
        CellStyle style = workbook.createCellStyle();
        Sheet sheet = workbook.createSheet("Delivery");

        // Задаем область печати
        //set print area with indexes
        workbook.setPrintArea(
                0, //sheet index
                0, //start column
                12, //end column
                0, //start row
                40 //end row
        );

        //set paper size
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        sheet.setAutobreaks(true);
        //set display grid lines or not
        sheet.setDisplayGridlines(true);
        //set print grid lines or not
        sheet.setPrintGridlines(true);

        int width = (int) (14 * 1.14388) * 256; // 1757;
//        sheet.autoSizeColumn(0);
        sheet.setColumnWidth(0, width);
        sheet.setColumnWidth(1, width);
        sheet.setColumnWidth(2, width);
        sheet.setColumnWidth(3, width);
        sheet.setColumnWidth(4, width);
        sheet.setColumnWidth(5, width);
        sheet.setColumnWidth(6, width);
        sheet.setColumnWidth(7, width);
        sheet.setColumnWidth(8, width);
        sheet.setColumnWidth(9, width);
        sheet.setColumnWidth(10, width);
        sheet.setColumnWidth(11, width);
        sheet.setColumnWidth(12, width);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        // Определение цвета граничных значений стиля
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        String header;
        Row row = sheet.createRow((short) 0);
        Cell cell = row.createCell(0);

        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Calibri");
        font.setColor(IndexedColors.BLACK.getIndex());
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);

        row = sheet.createRow((short) 0); // задаем номер исходной строки
//        row.setHeightInPoints(30.0f);
        cell = row.createCell(0);
        header = "Дата поставки";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(1);
        header = "Время поставки";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(2);
        header = "Данные на машину";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(3);
        header = "Данные на водителя";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(4);
        header = "Brand";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(5);
        header = "Номер заказа";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(6);
        header = "Тип поставки";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(7);
        header = "Поставщик";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(8);
        header = "Комментарии";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(9);
        header = "Магазин";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(10);
        header = "Количество мест";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(11);
        header = "Торг-12";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        cell = row.createCell(12);
        header = "Счет-фактура";
        cell.setCellValue(header);
        cell.setCellStyle(style);

        int rowNum = 1;
        for(DeliveryDto deliveryDto : deliveryDtos){
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(deliveryDto.getDeliveryDate()));
//            cell.setCellValue(deliveryDto.getDeliveryDate());
            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getDeliveryTime().getDeliveryTime());
            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getCarInfo());
            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getDriverInfo());
            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getBrand().getName());
            cell = row.createCell(5);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getOrderNumber());
            cell = row.createCell(6);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getDeliveryType().getType());
            cell = row.createCell(7);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getSender());
            cell = row.createCell(8);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getComment());
            cell = row.createCell(9);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getShop().getName());
            cell = row.createCell(10);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getNumberOfPlaces());
            cell = row.createCell(11);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getTorgNumber());
            cell = row.createCell(12);
            cell.setCellStyle(style);
            cell.setCellValue(deliveryDto.getInvoice());
        }
    }
}
