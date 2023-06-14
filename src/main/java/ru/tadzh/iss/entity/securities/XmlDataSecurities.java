package ru.tadzh.iss.entity.securities;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlDataSecurities {
    @XmlElement(name="rows")
    ListSecurities listSecurities;

}
