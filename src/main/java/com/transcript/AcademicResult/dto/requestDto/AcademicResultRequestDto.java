package com.transcript.AcademicResult.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicResultRequestDto {

    private MultipartFile universityLogoFile;

    private String universityLogo;

    private String universityName;

    private String universityAddress1;

    private String universityAddress2;

    private String docNumber;

    private String date;

    private String name;

    private String regNumber;

    private String degree;

    private String result;

    private String awardDate;

    private String collegeName;

    private String universityLocation;

}
