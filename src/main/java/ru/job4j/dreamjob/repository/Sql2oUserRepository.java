package ru.job4j.dreamjob.repository;

import ru.job4j.dreamjob.model.User;

import java.util.Optional;

public class Sql2oUserRepository implements UserRepository{
    @Override
    public Optional<User> save(User user) {
        /*
         try (var connection = sql2o.open()) {
            var sql = """
                    UPDATE candidates
                    SET name = :name,
                    description = :description,
                     creation_date = :creationDate,
                        city_id = :cityId,
                        file_id = :fileId
                    WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("name", candidate.getName())
                    .addParameter("description", candidate.getDescription())
                    .addParameter("creationDate", candidate.getCreationDate())
                    .addParameter("cityId", candidate.getCityId())
                    .addParameter("fileId", candidate.getFileId())
                    .addParameter("id", candidate.getId());
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
         */
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        /*
        try (var connection = sql2o.open()) {
            var query = connection
                    .createQuery("SELECT * FROM candidates WHERE id = :id");
            query.addParameter("id", id);
            var candidate = query.setColumnMappings(Candidate.COLUMN_MAPPING)
                    .executeAndFetchFirst(Candidate.class);
            return Optional.ofNullable(candidate);
         */
        return Optional.empty();
    }
}
