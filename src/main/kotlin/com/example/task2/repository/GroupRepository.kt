package com.example.task2.repository

import com.example.task2.models.Group
import com.example.task2.models.GroupDto
import org.springframework.stereotype.Repository
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException


@Repository
class GroupRepository {
    private companion object {
        const val URL = "jdbc:sqlite:C:/Users/gayal/Projects/task2/src/main/kotlin/com/example/task2/repository/SecretSanta.db"

        const val TABLE_NAME = "Groups"
        const val ID_COLUMN_NAME = "id"
        const val NAME_COLUMN_NAME = "name"
        const val DESCRIPTION_COLUMN_NAME = "description"
    }

    fun getGroups(): List<Group> {
        val selectQuery = "SELECT $ID_COLUMN_NAME, $NAME_COLUMN_NAME, $DESCRIPTION_COLUMN_NAME FROM $TABLE_NAME"
        val res = mutableListOf<Group>()

        try {
            DriverManager.getConnection(URL).use { conn ->
                conn.prepareStatement(selectQuery).use { pstmt ->
                    val rs = pstmt.executeQuery()
                    while (rs.next()) {
                        res.add(toGroup(rs))
                    }
                }
            }
        } catch (e: SQLException) {
            println(e.message)
        }

        return res
    }

//    fun getGroup(id: String): GroupWithParticipants {
//
//    }
//
    fun createGroup(groupData: GroupDto): Int {
        val query = "INSERT INTO $TABLE_NAME ($NAME_COLUMN_NAME, $DESCRIPTION_COLUMN_NAME) VALUES (?, ?)"

        try {
            DriverManager.getConnection(URL).use { conn ->
                conn.prepareStatement(query).use { pstmt ->
                    pstmt.setString(1, groupData.name)
                    pstmt.setString(2, groupData.description)
                    pstmt.executeUpdate()

                    val rs = pstmt.generatedKeys
                    if (rs.next()) {
                        val id = rs.getInt(1)
                        println("Inserted ID -$id")
                        return id
                    }
                }
            }
        } catch (e: SQLException) {
            println(e.message)
        }

        return -1
    }
//
//    fun updateGroup(id: String): Group {
//
//    }
//
//    fun deleteGroup(id: String) {
//
//    }

    private fun toGroup(rs: ResultSet) = Group(
        rs.getInt(ID_COLUMN_NAME),
        rs.getString(NAME_COLUMN_NAME),
        rs.getString(DESCRIPTION_COLUMN_NAME)
    )
}