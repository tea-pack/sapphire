package com.github.tea_pack.sapphire.mail;

import com.github.tea_pack.sapphire.statistics.UniqueStatsReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        String status = emailService.sendSimpleEmail(details);

        return status;
    }

    @PostMapping("/sendStats")
    public String sendStatistics(@RequestBody EmailDetails details) {
        UniqueStatsReport report = new UniqueStatsReport();
        // report.addEntry(new ReportEntry("Первый канал", "Программа 1", 120, 42));
        // report.addEntry(new ReportEntry("Третий канал", "Новости", 450, 12));
        // report.addEntry(new ReportEntry("Четверг", "Лучший боевик", 120, 423));
        // report.addEntry(new ReportEntry("Автобус", "Мстители", 764, 554));
        // report.addEntry(new ReportEntry("Карусель", "Заголовок", 92, 23));
        report.calcStat();

        details.setSubject("Статистика по популярности");
        details.setMsgBody(
                "<table cellspacing=\"15\" style=\"color: #900012; font-family: monospace; font-size: 12px; font-weight: normal; line-height: 20px;\">"
                        + report.toHtmlTableRow() + "</table>");

        String status = emailService.sendMailHTML(details);
        return status;
    }
}
