package com.untouchable.everytime.School.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class School implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    String schoolName;
    String schoolLocation;
    String schoolTell;
    String schoolFax;
    String schoolUrlHome;
    String schoolUrlStudyRoom;
    String schoolUrlShuttle;
    String schoolUrlNotice;
    String schoolUrlCalendar;
    String schoolUrlLibrary;
}
