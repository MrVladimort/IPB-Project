package pl.pjatk.ipb.project.control.service.impl;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import pl.pjatk.ipb.project.boundary.dto.TestDTO;
import pl.pjatk.ipb.project.control.dao.TestDAO;
import pl.pjatk.ipb.project.control.entity.TestEntity;
import pl.pjatk.ipb.project.controller.exceptions.EntityNotFoundException;
import pl.pjatk.ipb.project.controller.exceptions.TestsFailedException;
import pl.pjatk.ipb.project.control.mapper.ProjectMapper;
import pl.pjatk.ipb.project.control.service.TestService;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
  @NonNull TestDAO testDao;
  @NonNull ProjectMapper mapper;

  @Override
  public List<TestDTO> getTests() {
    return StreamSupport.stream(testDao.findAll().spliterator(), false)
        .limit(10)
        .map(mapper::testDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public Double checkTestsResult(List<TestDTO> tests) {
    log.info("Check results for tests: {}", tests);

    AtomicDouble testsResult = new AtomicDouble(0.0);

    tests.forEach(
        testDto -> {
          TestEntity testEntity =
              testDao
                  .findByQuestion(testDto.getQuestion())
                  .orElseThrow(EntityNotFoundException::new);
          testsResult.addAndGet(
              testDto.getSubmittedAnswer().equalsIgnoreCase(testEntity.getCorrectAnswer())
                  ? 100.0 / tests.size()
                  : 0.0);
        });

    return testsResult.get();
  }

  @Bean
  public void loadTestsData() {
    testDao.save(
        TestEntity.builder()
            .question("Lorum Ipsum?")
            .answers(List.of("Lorum", "Ipsum", "Hipsum", "Lorum Ipsum"))
            .correctAnswer("Lorum Ipsum")
            .build());

    testDao.save(
        TestEntity.builder()
            .question("Keks?")
            .answers(List.of("Heks", "Keks", "Megs", "Szpeks"))
            .correctAnswer("Keks")
            .build());

    testDao.save(
        TestEntity.builder()
            .question("Terror?")
            .answers(List.of("Terror", "Ipsum", "Hipsum", "Lorum Ipsum"))
            .correctAnswer("Terror")
            .build());

    testDao.save(
        TestEntity.builder()
            .question("Hm?")
            .answers(List.of("Lorum", "Ipsum", "Hm", "Lorum Ipsum"))
            .correctAnswer("Hm")
            .build());

    testDao.save(
        TestEntity.builder()
            .question("Da?")
            .answers(List.of("Lorum", "Ipsum", "Hipsum", "Da"))
            .correctAnswer("Da")
            .build());
  }
}
