package ru.tadzh.iss.demXML.securities;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDocSecurities {
    @XmlElement(name="data")
    XmlDataSecurities xmlDataSecurities;
}
