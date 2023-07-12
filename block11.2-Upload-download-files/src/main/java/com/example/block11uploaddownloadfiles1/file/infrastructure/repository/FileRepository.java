package com.example.block11uploaddownloadfiles1.file.infrastructure.repository;

import com.example.block11uploaddownloadfiles1.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
