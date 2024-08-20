package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Published_Applications;
import com.example.capstone2.Repository.Published_ApplicationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Published_ApplicationsService {
    private Published_ApplicationsRepository published_applicationsRepository;

    public List<Published_Applications> getAllPublished_Applications() {
        return published_applicationsRepository.findAll();
    }

    public void add_published_application(Published_Applications published_application) {
        published_applicationsRepository.save(published_application);
    }

    public void delete_published_application(int published_application_id) {
        Published_Applications p = published_applicationsRepository.findByPublished_Applications_id(published_application_id);
        if (p==null){
            throw new ApiException("application not found");
        }
        published_applicationsRepository.delete(p);
    }

    public void update_published_application(int published_application_id, Published_Applications published_application) {
        Published_Applications p = published_applicationsRepository.findByPublished_Applications_id(published_application_id);

        if (p==null){
            throw new ApiException("application not found");
        }
        p.setApplication_id(published_application.getApplication_id());
        p.setTeacher_id(published_application.getTeacher_id());
        published_applicationsRepository.save(p);
    }
}
