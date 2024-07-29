package com.test.grpcclient.client;

import com.test.grpcserver.LabReport;
import com.test.grpcserver.LabReportResponse;
import com.test.grpcserver.LabReportServiceGrpc;
import com.test.grpcserver.SampleRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class LabReportClientService {

    @GrpcClient("lab-service") //lab-service is the name defined in your application's configuration properties of client.
    private LabReportServiceGrpc.LabReportServiceBlockingStub labReportServiceBlockingStub;

    public void testClient(){
        // Start calling the `testSample` method
        SampleRequest sampleRequest = SampleRequest.newBuilder()
                .setLabReport(LabReport.newBuilder()
                        .setSampleType("Blood")
                        .setTestType("CBC Test")
                        .build())
                .build();


        LabReportResponse labReportResponse = labReportServiceBlockingStub.testSample(sampleRequest);
        System.out.println("Response for the first call: " + labReportResponse.getResult());

        // Call to the `testSample` method is successfully completed. Now calling the `testSampleManyTimes` method.
        // Hold the thread for 10s before calling the other method.
        System.out.println("### Initiating the second request ###");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SampleRequest sampleRequest2 = SampleRequest.newBuilder()
                .setLabReport(LabReport.newBuilder()
                        .setSampleType("Urine")
                        .setTestType("Urine Routine Test")
                        .build())
                .build();

        System.out.println("Response for the second call: ");
        labReportServiceBlockingStub.testSampleManyTimes(sampleRequest2).forEachRemaining(parkResponseManyTimes -> {
            System.out.println(parkResponseManyTimes.getResult());
        });

    }
}
