package de.rgse.brewlogs.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.rgse.brewlogs.domain.BrewLog;
import de.rgse.brewlogs.domain.User;
import de.rgse.brewlogs.domain.converters.LocalDateTimeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrewLogVo {

    @JsonProperty
    private long id;

    @JsonProperty
    private Date created;

    @JsonProperty
    private String name;

    @JsonProperty
    private UserVo user;

    public static BrewLogVo of(@NotNull BrewLog brewLog) {
        BrewLogVo brewLogVo = new BrewLogVo();

        brewLogVo.id = brewLog.getId();
        brewLogVo.created = new LocalDateTimeConverter().convertToDatabaseColumn(brewLog.getCreated());
        brewLogVo.name = brewLog.getName();
        brewLogVo.user = UserVo.of(brewLog.getUser());

        return brewLogVo;
    }

    public static List<BrewLogVo> of(List<BrewLog> brewLogs) {
        List<BrewLogVo> brewLogVos = new ArrayList<>();

        if(brewLogs != null) {
            brewLogs.forEach(log -> brewLogVos.add(BrewLogVo.of(log)));
        }

        return brewLogVos;
    }
}
