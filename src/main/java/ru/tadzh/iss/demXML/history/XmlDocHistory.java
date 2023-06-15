package ru.tadzh.iss.demXML.history;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@Data
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDocHistory {
    @XmlElement(name="data")
    List <XmlDataHistory> xmlDataHistory;

}
