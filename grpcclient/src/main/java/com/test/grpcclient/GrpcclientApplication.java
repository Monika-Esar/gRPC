package com.test.grpcclient;

import com.test.grpcclient.client.LabReportClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GrpcclientApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(GrpcclientApplication.class, args);
//    }
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(GrpcclientApplication.class, args);
        LabReportClientService grpcClientClass = context.getBean(LabReportClientService.class);
        grpcClientClass.testClient();
    }


}
