package ru.tadzh.iss.demXML.securities;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDataSecurities {
    @XmlElement(name="rows")
    XmlListSecurities xmlListSecurities;

}
