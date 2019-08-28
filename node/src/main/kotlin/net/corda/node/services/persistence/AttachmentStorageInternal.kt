package net.corda.node.services.persistence

import net.corda.core.contracts.Attachment
import net.corda.core.node.services.AttachmentId
import net.corda.core.node.services.AttachmentStorage
import net.corda.nodeapi.exceptions.DuplicateAttachmentException
import java.io.InputStream

interface AttachmentStorageInternal : AttachmentStorage {

    /**
     * This is the same as [importAttachment] expect there are no checks done on the uploader field. This API is internal
     * and is only for the node.
     */
    fun privilegedImportAttachment(jar: InputStream, uploader: String, filename: String?): AttachmentId

    /**
     * Similar to above but returns existing [AttachmentId] instead of throwing [DuplicateAttachmentException]
     */
    fun privilegedImportOrGetAttachment(jar: InputStream, uploader: String, filename: String?): AttachmentId

    /**
     * Get all attachments stored within the node paired to their file name's.
     */
    fun getAllAttachments(): List<Pair<String?, Attachment>>
}