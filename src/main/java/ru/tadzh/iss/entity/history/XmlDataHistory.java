package ru.tadzh.iss.entity.history;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDataHistory {
    @XmlElement(name="rows")
    ListHistory listHistory;
}
