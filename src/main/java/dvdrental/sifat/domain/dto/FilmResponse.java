package dvdrental.sifat.domain.dto;

import dvdrental.sifat.domain.entity.FilmsEntity;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "FilmResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class FilmResponse {
    @XmlElement(name = "ResponseStatus")
    private ResponseStatus responseStatus;
    @XmlElement(name = "Films")
    private List<FilmsEntity> films;
}
