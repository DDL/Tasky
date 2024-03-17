package com.ddoczi.tasky.agenda.data.mapper

import com.ddoczi.tasky.agenda.data.local.entity.ReminderEntity
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.core.util.toLocalDateTime
import com.ddoczi.tasky.core.util.toLong

fun ReminderEntity.toReminderAgendaItem(): AgendaItem.Reminder {
    return AgendaItem.Reminder(
        reminderId = this.reminderId,
        reminderTitle = this.title,
        reminderDescription = this.description,
        reminderDate = this.reminderDateTime.toLocalDateTime(),
        reminderRemindAt = this.remindAt.toLocalDateTime()
    )
}

fun AgendaItem.Reminder.toReminderEntity(): ReminderEntity {
    return ReminderEntity(
        reminderId = this.reminderId,
        title = this.reminderTitle,
        description = this.reminderDescription,
        remindAt = this.reminderRemindAt.toLong(),
        reminderDateTime = this.reminderDate.toLong()
    )
}
