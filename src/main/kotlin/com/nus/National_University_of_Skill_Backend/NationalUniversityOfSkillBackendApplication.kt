package com.nus.National_University_of_Skill_Backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("com.nus.National_University_of_Skill_Backend.feature")
@EnableJpaRepositories("com.nus.National_University_of_Skill_Backend.feature")
class NationalUniversityOfSkillBackendApplication

fun main(args: Array<String>) {
	runApplication<NationalUniversityOfSkillBackendApplication>(*args)
}
