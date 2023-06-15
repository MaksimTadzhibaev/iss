package ru.tadzh.iss.demXML.securities;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlListSecurities {
    @XmlElement(name="row")
    List<XmlSecurities> securities;
}
