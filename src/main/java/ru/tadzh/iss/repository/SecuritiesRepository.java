package ru.tadzh.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.tadzh.iss.entity.Securities;

public interface SecuritiesRepository extends JpaRepository<Securities, Long>, JpaSpecificationExecutor<Securities> {
}
