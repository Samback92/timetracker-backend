package timetracker_backend.timetracker_backend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import timetracker_backend.timetracker_backend.Model.Task;
public interface TaskRepository extends MongoRepository<Task, String> {

}
