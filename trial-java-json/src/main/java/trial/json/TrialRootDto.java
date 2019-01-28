package trial.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
public class TrialRootDto {

    @JsonProperty("arrayVal")
    private List<Integer> list;

    private Boolean booleanVal;

    private String colorVal;

    private Object nullVal;

    private Integer numberVal;

    private TrialSubDto objectVal;

    private String stringVal;
}
