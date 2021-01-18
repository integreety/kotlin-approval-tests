package com.integreety.activityengine.extension

import com.oneeyedmen.okeydoke.ApproverFactories.fileSystemApproverFactory
import com.oneeyedmen.okeydoke.junit5.ApprovalsExtension
import java.io.File

internal class ResourcesApprovalsExtension : ApprovalsExtension(
        fileSystemApproverFactory(File("src/test/resources/approved"))
)