package com.camunda.auditTrail.service;

import com.camunda.auditTrail.dto.MJUTDisplayDTO;
import com.camunda.auditTrail.entity.ProcessInstanceEntity;
import com.camunda.auditTrail.repository.ProcessInstanceRepository;
import com.camunda.auditTrail.response.SearchTaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MjutDtoService {

    @Autowired
    ProcessInstanceRepository processInstanceRepository;

    public List<MJUTDisplayDTO> prepareMjutDisplayData(List<SearchTaskResponse> searchTaskResponses){
        return searchTaskResponses.stream()
                .map(task->{
                    ProcessInstanceEntity mjutId= processInstanceRepository
                            .findByprocessInstanceKey(
                                    task.getProcessInstanceKey()
                            );
                    if(mjutId!=null){
                        return new MJUTDisplayDTO(mjutId.getMjutId(), task.getFormKey(), task.getAssignee());
                    }else{
                        return null;
                    }


                })
                .filter(mjutDisplayDTO -> mjutDisplayDTO!=null)
                .collect(Collectors.toList());
    }
}
