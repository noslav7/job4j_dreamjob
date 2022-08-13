package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private static AtomicInteger candidate = new AtomicInteger(3);
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Dmitriy", "first candidate", new Date()));
        candidates.put(2, new Candidate(2, "Andrew", "second candidate", new Date()));
        candidates.put(3, new Candidate(3, "Svetlana", "third candidate", new Date()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(candidate.getId());
        candidates.put(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        candidates.replace(candidate.getId(), candidate);
    }
}
