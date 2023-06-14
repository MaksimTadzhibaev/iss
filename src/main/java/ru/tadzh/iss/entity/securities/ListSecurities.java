package ru.tadzh.iss.entity.securities;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@ToString
@XmlRootElement(name = "rows")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListSecurities {
    @XmlElement(name="row")
    List<Securities> securities;
}
