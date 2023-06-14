package ru.tadzh.iss.entity.history;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Date;

@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class History {
    @XmlAttribute(name = "TRADEDATE")//дата
    String tradeDate;
    @XmlAttribute(name = "SECID")
    String secId;
    @XmlAttribute(name = "NUMTRADES")
    String numTrades;
    @XmlAttribute(name = "OPEN")
    String open;

}
