package com.likelion.korea_hospital.parser;

import com.likelion.korea_hospital.domain.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Spring Boot가 스캔을 해서 등록한 Bean을 Test에서 쓸 수 있게 함
// @SpringBootTest
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";
    String line770 = "\"770\",\"의원\",\"01_01_02_P\",\"5710000\",\"PHMA119974360079041100017\",\"19970706\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"295-5001\",\"\",\"362856\",\"충청북도 청주시 서원구 분평동 1202번지 2호\",\"충청북도 청주시 서원구 월평로 69 (분평동)\",\"28792\",\"유림가정의학과의원\",\"20170905183709\",\"I\",\"2018-08-31 23:59:59.0\",\"의원\",\"244003.914471\",\"345700.272648\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",";

    @Autowired // new ReadLineContext()를 하지 않도록 해줌 --> Singleton --> GC가 생성되지 않도록 도와줌
    ReadLineContext<Hospital> hospitalReadLineContext;


    @Test
    @DisplayName("10만건 이상이 잘 등록되었는지 확인")
    void name() throws IOException {
        HospitalParser hospitalParser = new HospitalParser();
        ReadLineContext<Hospital> readLineContext = new ReadLineContext<>(new HospitalParser());
        List<Hospital> hospitalList= readLineContext.readByLine("C:\\Users\\yeonji\\Desktop\\fulldata_01_01_02_P_의원_utf_8.csv");
        // 파일을 utf-8로 변환하여 돌리기
        assertTrue(hospitalList.size()>100000);
    }


    @Test
    @DisplayName("csv 1줄은 Hospital로 잘 만드는지 TEST")
    void convertToHospital() throws IOException {


        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());
    }


}