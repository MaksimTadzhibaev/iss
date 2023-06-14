package ru.tadzh.iss.entity.history;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@Data
@ToString
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDocHistory {
    @XmlElement(name="data")
    List <XmlDataHistory> xmlDataHistory;

}