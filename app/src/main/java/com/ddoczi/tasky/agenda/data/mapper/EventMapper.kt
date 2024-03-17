package com.ddoczi.tasky.agenda.data.mapper

import com.ddoczi.tasky.agenda.data.local.entity.EventEntity
import com.ddoczi.tasky.agenda.domain.model.AgendaItem
import com.ddoczi.tasky.core.util.toLocalDateTime
import com.ddoczi.tasky.core.util.toLong

fun EventEntity.toEventAgendaItem(): AgendaItem.Event {
    return AgendaItem.Event(
        eventId = this.eventId,
        eventTitle = this.title,
        eventDescription = this.description,
        eventRemindAt = this.remindAt.toLocalDateTime(),
        eventFromDate = this.fromDateTime.toLocalDateTime(),
        eventToDate = this.toDateTime.toLocalDateTime()
    )
}

fun AgendaItem.Event.toEventEntity(): EventEntity {
    return EventEntity(
        eventId = this.eventId,
        title = this.eventTitle,
        description = this.eventDescription,
        remindAt = this.eventRemindAt.toLong(),
        fromDateTime = this.eventFromDate.toLong(),
        toDateTime = this.eventToDate.toLong()
    )
}

