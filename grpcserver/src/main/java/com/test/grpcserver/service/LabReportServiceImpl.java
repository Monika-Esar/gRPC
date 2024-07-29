package com.test.grpcserver.service;

import com.test.grpcserver.LabReportResponse;
import com.test.grpcserver.LabReportResponseManyTimes;
import com.test.grpcserver.LabReportServiceGrpc;
import com.test.grpcserver.SampleRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class LabReportServiceImpl extends LabReportServiceGrpc.LabReportServiceImplBase {
    @Override
    public void testSample(SampleRequest request, StreamObserver<LabReportResponse> responseObserver) {

        String testType = request.getLabReport().getTestType();
        String sampleType = request.getLabReport().getSampleType();

        System.out.println("The " + testType + " using " + sampleType + " sent for testing.");
        String resultMsg = "The report of " + testType + " using " + sampleType + " is completed.";

        LabReportResponse labReportResponse = LabReportResponse
                .newBuilder()
                .setResult(resultMsg)
                .build();

        // Send the response to the client.
        responseObserver.onNext(labReportResponse);

        // Notifies the customer that the call is completed.
        responseObserver.onCompleted();
        System.out.println(resultMsg);

    }

    @Override
    public void testSampleManyTimes(SampleRequest request, StreamObserver<LabReportResponseManyTimes> responseObserver) {

        String testType = request.getLabReport().getTestType();
        String sampleType = request.getLabReport().getSampleType();

        System.out.println("The " + testType + " using " + sampleType + " sent for testing.");
        String response1 = "The report of " + testType + " using " + sampleType + " is completed.";

        LabReportResponseManyTimes labReportResponseManyTimes = LabReportResponseManyTimes
                .newBuilder()
                .setResult(response1)
                .build();
        responseObserver.onNext(labReportResponseManyTimes);

        // Hold the thread for 5s before sending the second response.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Build and send the second response.
        String response2 = "The report is delayed.";
        LabReportResponseManyTimes labReportResponseManyTimes1 = LabReportResponseManyTimes
                .newBuilder()
                .setResult(response2)
                .build();
        responseObserver.onNext(labReportResponseManyTimes1);

        // Hold the thread for 5s before sending the third response.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Build and send the third response.
        String response3 = "The report of " + testType + " using " + sampleType + " is completed and is passed for final approval.";

        LabReportResponseManyTimes labReportResponseManyTimes2 = LabReportResponseManyTimes
                .newBuilder()
                .setResult(response3)
                .build();
        responseObserver.onNext(labReportResponseManyTimes2);

        // Complete the communication.
        responseObserver.onCompleted();

        System.out.println(response3);
    }
}
