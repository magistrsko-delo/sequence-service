package si.fri.mag.grpc;


import com.kumuluz.ee.grpc.annotations.GrpcService;
import grpc.SequenceMetadataGrpc;
import grpc.SequencemetadataService;
import io.grpc.stub.StreamObserver;
import si.fri.mag.DTO.SequenceDTO;
import si.fri.mag.DTO.input.SequenceNew;
import si.fri.mag.DTO.responses.SequenceMediaDTO;
import si.fri.mag.services.SequenceMediaService;
import si.fri.mag.services.SequenceService;

import javax.enterprise.inject.spi.CDI;
import java.util.ArrayList;
import java.util.List;

@GrpcService
public class SequenceMetadataServiceImpl extends SequenceMetadataGrpc.SequenceMetadataImplBase {
    SequenceService sequenceService;
    SequenceMediaService sequenceMediaService;

    @Override
    public void getProjectSequences(SequencemetadataService.GetProjectSequencesRequest request, StreamObserver<SequencemetadataService.ProjectSequencesResponse> responseObserver) {
        sequenceService = CDI.current().select(SequenceService.class).get();

        List<SequenceDTO> sequenceDTOS = sequenceService.getProjectSequences(request.getProjectId());

        List<SequencemetadataService.Sequence> sequenceArrayListRsp = new ArrayList<>();
        for (SequenceDTO sequenceDTO : sequenceDTOS) {
            sequenceArrayListRsp.add(this.buildSequence(sequenceDTO));
        }

        SequencemetadataService.ProjectSequencesResponse response = SequencemetadataService.ProjectSequencesResponse
                .newBuilder()
                .addAllData(sequenceArrayListRsp)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getSequenceMedia(SequencemetadataService.SequenceIdRequest request, StreamObserver<SequencemetadataService.SequenceMediaResponse> responseObserver) {

        sequenceService = CDI.current().select(SequenceService.class).get();

        SequenceMediaDTO sequenceMediaDTO = sequenceService.getOneSequenceMedia(request.getSequenceId());

        responseObserver.onNext(this.buildSequenceMediaResponse(sequenceMediaDTO));
        responseObserver.onCompleted();
    }

    @Override
    public void createSequence(SequencemetadataService.NewSequenceRequest request, StreamObserver<SequencemetadataService.SequenceMediaResponse> responseObserver) {

        sequenceService = CDI.current().select(SequenceService.class).get();
        SequenceNew sequenceNew = new SequenceNew();
        sequenceNew.setName(request.getName());
        sequenceNew.setProjectId(request.getProjectId());
        sequenceNew.setThumbnail(request.getThumbnail());

        SequenceMediaDTO sequenceMediaDTO = sequenceService.createNewSequence(sequenceNew);

        responseObserver.onNext(this.buildSequenceMediaResponse(sequenceMediaDTO));
        responseObserver.onCompleted();
    }

    @Override
    public void updateSequence(SequencemetadataService.UpdateSequenceRequest request, StreamObserver<SequencemetadataService.SequenceMediaResponse> responseObserver) {
        sequenceService = CDI.current().select(SequenceService.class).get();

        SequenceDTO sequenceDTO = new SequenceDTO();
        sequenceDTO.setSequenceId(request.getSequenceId());
        sequenceDTO.setName(request.getName());
        sequenceDTO.setStatus(request.getStatus());
        sequenceDTO.setProjectId(request.getProjectId());
        sequenceDTO.setThumbnail(request.getThumbnail());

        SequenceMediaDTO sequenceMediaDTO = sequenceService.updateSequence(sequenceDTO);
        responseObserver.onNext(this.buildSequenceMediaResponse(sequenceMediaDTO));
        responseObserver.onCompleted();
    }

    @Override
    public void deleteSequence(SequencemetadataService.SequenceIdRequest request, StreamObserver<SequencemetadataService.StatusResponse> responseObserver) {
        sequenceService = CDI.current().select(SequenceService.class).get();

        boolean isDeleted = sequenceService.deleteSequence(request.getSequenceId());

        SequencemetadataService.StatusResponse response = SequencemetadataService.StatusResponse.newBuilder()
                .setStatus(200)
                .setMessage(isDeleted ? "Sequence with id: " + request.getSequenceId() + " deleted" : "not deleted")
                .setData(isDeleted)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addMediaToSequence(SequencemetadataService.SequenceMediaRequest request, StreamObserver<SequencemetadataService.StatusResponse> responseObserver) {
        sequenceMediaService = CDI.current().select(SequenceMediaService.class).get();

        boolean isMediaAdded = sequenceMediaService.addMediaToSequence(request.getSequenceId(), request.getMediaId());

        SequencemetadataService.StatusResponse response = SequencemetadataService.StatusResponse.newBuilder()
                .setStatus(200)
                .setMessage(isMediaAdded ? "Media: " + request.getMediaId() + " added to sequence with id: " + request.getSequenceId() : "error adding media to sequence")
                .setData(isMediaAdded)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteMediaFromSequence(SequencemetadataService.SequenceMediaRequest request, StreamObserver<SequencemetadataService.StatusResponse> responseObserver) {
        sequenceMediaService = CDI.current().select(SequenceMediaService.class).get();

        boolean isMediaAdded = sequenceMediaService.deleteMediaInSequence(request.getSequenceId(), request.getMediaId());

        SequencemetadataService.StatusResponse response = SequencemetadataService.StatusResponse.newBuilder()
                .setStatus(200)
                .setMessage(isMediaAdded ? "Media: " + request.getMediaId() + " deleted from sequence with id: " + request.getSequenceId() : "error deleting media to sequence")
                .setData(isMediaAdded)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private SequencemetadataService.SequenceMediaResponse buildSequenceMediaResponse(SequenceMediaDTO sequenceMediaDTO) {

        return SequencemetadataService.SequenceMediaResponse
                .newBuilder()
                .setSequence(this.buildSequence(sequenceMediaDTO.getSequence()))
                .addAllMediaIds(sequenceMediaDTO.getMediaIds())
                .build();

    }

    private SequencemetadataService.Sequence buildSequence(SequenceDTO sequenceDTO) {
        return SequencemetadataService.Sequence.newBuilder()
                .setSequenceId(sequenceDTO.getSequenceId())
                .setName(sequenceDTO.getName())
                .setProjectId(sequenceDTO.getProjectId())
                .setThumbnail(sequenceDTO.getThumbnail())
                .setStatus(sequenceDTO.getStatus())
                .setCreatedAt(sequenceDTO.getCreatedAt().getTime())
                .setUpdatedAt(sequenceDTO.getUpdatedAt().getTime())
                .build();
    }
}
