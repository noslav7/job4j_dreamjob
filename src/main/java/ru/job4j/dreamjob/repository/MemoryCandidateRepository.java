package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {
    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Иванов Иван Иванович",
                "Trainee candidate", LocalDateTime.now()));
        save(new Candidate(0, "Сергеев Сергей Сергеевич",
                "Junior candidate", LocalDateTime.now()));
        save(new Candidate(0, "Семенов Семен Семенович",
                "Junior+ candidate", LocalDateTime.now()));
        save(new Candidate(0, "Алексеев Алексей Алексеевич",
                "Middle candidate", LocalDateTime.now()));
        save(new Candidate(0, "Ермолин Вадим Александрович",
                "Middle+ candidate", LocalDateTime.now()));
        save(new Candidate(0, "Арсентьев Петр Сергеевич",
                "Senior candidate", LocalDateTime.now()));
    }

    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldCandidate) ->
                new Candidate(oldCandidate.getId(), candidate.getName())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
