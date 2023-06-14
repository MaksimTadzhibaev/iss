package ru.tadzh.iss.entity.securities;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@Data
@ToString
@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDocSecurities {
    @XmlElement(name="data")
    XmlDataSecurities xmlDataSecurities;
}
