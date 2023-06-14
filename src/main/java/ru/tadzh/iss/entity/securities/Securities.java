package ru.tadzh.iss.entity.securities;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class Securities {
    @XmlAttribute(name = "id")
    int id;
    @XmlAttribute(name = "secid")
    String secId;
    @XmlAttribute(name = "regnumber")
    String regNumber;
    @XmlAttribute(name = "name")
    String name;
    @XmlAttribute(name = "emitent_title")
    String emitentTitle;
}
