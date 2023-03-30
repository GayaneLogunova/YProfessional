package com.example.task2.controller

import com.example.task2.models.Group
import com.example.task2.models.GroupDto
import com.example.task2.models.GroupWithParticipants
import com.example.task2.repository.GroupRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RestController
class GroupController(private val repository: GroupRepository) {

    @GetMapping("/groups")
    fun getGroups(): List<Group> {
        return repository.getGroups()
    }
//
//    @GetMapping("/group/{id}")
//    fun getGroup(@PathVariable("id") id: String): GroupWithParticipants =
//        repository.getGroup(id)
//
    @PostMapping("/group")
    fun createGroup(@RequestBody groupData: GroupDto): Int =
        repository.createGroup(groupData)

//    @PutMapping("/group/{id}")
//    fun updateGroup(@PathVariable("id") id: String): Group =
//        repository.updateGroup(id)
//
//    @DeleteMapping("/group/{id}")
//    fun deleteGroup(@PathVariable("id") id: String) =
//        repository.deleteGroup(id)
}