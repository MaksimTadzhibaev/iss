package ru.tadzh.iss.demXML.securities;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlSecurities {
    @XmlAttribute(name = "secid")
    String secId;
    @XmlAttribute(name = "regnumber")
    String regNumber;
    @XmlAttribute(name = "name")
    String name;
    @XmlAttribute(name = "emitent_title")
    String emitentTitle;
}
