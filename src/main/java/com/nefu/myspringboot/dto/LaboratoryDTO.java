package com.nefu.myspringboot.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LaboratoryDTO {
    private Long lid;
    @ApiModelProperty("实验室编号")
    private Long number;
    @ApiModelProperty("实验室机器数量")
    private  Integer computerNumber;
    private List<ScheduleDTO> schedules;
}
