package com.alaitp.job.description.api.constant;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface CategoryConst {
    String PL = "PROGRAMMING_LANGUAGE";  // programming language,  e.g. Java, Python
    String OL = "OTHER_LANGUAGE";  // non-programming language, e.g. 'CSS', 'HTML'
    String LB = "LIBRARY";  // library, e.g. React, Bootstrap
    String FW = "FRAMEWORK";  // framework e.g. Spring, Django
    String CS = "COMPUTER_SCIENCE";  // computer science, low level knowledge
    String AI = "AI";  // Machine Learning, Deep Learning
    String PT = "PROTOCOL";  // e.g 'HTTP', 'CORS'
    String DS = "DATA_STORAGE";  // databases, MySQL, MongoDB
    String DT = "DATA_TRANSMISSION";  // JSON, RabbitMQ, Kafka
    String DV = "DIVISION";  // software development division at high level e.g. Front end, Data, Security, Network
    String PS = "POSITION";  // specific job position, personal
    String WE = "WORK_EXPERIENCE";  // years of work experience
    String OS = "OS";  // operating system
    String SV = "SERVER";  // Nginx, Tomcat, Node,js
    String AP = "APPROACH";  // software development approach e.g. TDD, AGILE
    String SE = "SOFTWARE_ENGINEERING";  // software engineering process e.g development, testing, optimization
    String PF = "PLATFORM";  // platforms for software development e.g AWS, Github
    String GE = "GENERAL";  // individual non-specific skills. e.g 'technical best practises', 'cloud services'
    String SF = "SOFT_SKILL";  // e.g. skills of interacting with other people 'active communicator', 'technical leadership'
    String TL = "TOOL";  // tools for software development work e.g. IDEs
    String AT = "ARCHITECT";  // software development architect. e.g. 'REST API'
    String PD = "SOFTWARE_PRODUCT";  // product to build
    String QL = "QUALITY";  // code/software quality e.g. easy-to-understand, testable
    String OF = "OFFER";  // salary, benefits
    String TM = "TEAM";  // team description/culture
    String CP = "COMPANY";  // company type e.g. startup, FinTech
    Map<String, String> CATEGORY_MAP = Map.ofEntries(
            Map.entry("pl", PL),
            Map.entry("ol", OL),
            Map.entry("lb", LB),
            Map.entry("fw", FW),
            Map.entry("cs", CS),
            Map.entry("ai", AI),
            Map.entry("pt", PT),
            Map.entry("ds", DS),
            Map.entry("dt", DT),
            Map.entry("dv", DV),
            Map.entry("ps", PS),
            Map.entry("we", WE),
            Map.entry("os", OS),
            Map.entry("sv", SV),
            Map.entry("ap", AP),
            Map.entry("se", SE),
            Map.entry("pf", PF),
            Map.entry("ge", GE),
            Map.entry("sf", SF),
            Map.entry("tl", TL),
            Map.entry("at", AT),
            Map.entry("pd", PD),
            Map.entry("ql", QL),
            Map.entry("of", OF),
            Map.entry("tm", TM),
            Map.entry("cp", CP)
    );
    Set<String> ALL_CATEGORY = new HashSet<>(CATEGORY_MAP.values());
}
