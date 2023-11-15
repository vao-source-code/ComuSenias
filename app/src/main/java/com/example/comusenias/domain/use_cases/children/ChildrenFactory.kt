package com.example.comusenias.domain.use_cases.children

data class ChildrenFactory(
    val createChildren: CreateChildren,
    val getChildrenById: GetChildrenById,
    val saveImageChildren: SaveImageChildren,
    val updateChildren: UpdateChildren,
    val integrateChildrenWithSpecialist: IntegrateChildrenWithSpecialist,
    val updateLevelChildren: UpdateLevelChildren,
)