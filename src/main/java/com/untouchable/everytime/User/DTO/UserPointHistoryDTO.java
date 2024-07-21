package com.untouchable.everytime.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPointHistoryDTO {

    Long userPoint_PK;

    Long user_PK;

    Long point;

}
