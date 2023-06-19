package ru.tadzh.iss.demXML.history;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDataHistory {
    @XmlElement(name="rows")
    XmlListHistory xmlListHistory;
}
