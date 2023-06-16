package ru.tadzh.iss.demXML.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlHistory {
    @XmlAttribute(name = "TRADEDATE")//дата
    String tradeDate;
    @XmlAttribute(name = "SECID")
    String secId;
    @XmlAttribute(name = "NUMTRADES")
    String numTrades;
    @XmlAttribute(name = "OPEN")
    String open;
}
