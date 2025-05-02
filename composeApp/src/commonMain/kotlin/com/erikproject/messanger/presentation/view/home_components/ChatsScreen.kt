import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erikproject.messanger.presentation.viewmodel.ChatsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.*
import kotlin.time.Duration.Companion.days

/**
 * Модель данных чата, соответствующая таблице local_chats
 */
data class ChatItem(
    val id: Long,
    val type: ChatType,
    val name: String?,
    val description: String?,
    val creatorId: Long?,
    val createdAt: Long,
    val avatarUrl: String?,
    val avatarLocalPath: String?,
    val lastMessageId: Long?,
    val lastMessageText: String?,
    val lastMessageTime: Long?,
    val unreadCount: Int,
    val lastSyncTime: Long,
    val isDraft: Boolean,
    val lastDraftText: String?,
    val members: List<ChatMember> = emptyList()
)

/**
 * Модель данных участника чата, соответствующая таблице local_chat_members
 */
data class ChatMember(
    val chatId: Long,
    val userId: Long,
    val role: String,
    val joinedAt: Long?,
    val lastSyncTime: Long
)

/**
 * Типы чатов
 */
enum class ChatType {
    PERSONAL, // Личный чат с одним пользователем
    GROUP,    // Групповой чат
    CHANNEL,  // Канал (сообщения могут отправлять только администраторы)
    SELF      // Чат с самим собой (например, избранное)
}


/**
 * Основной экран списка чатов
 */
@Composable
fun ChatsScreen(
    viewModel: ChatsViewModel,
) {
    val chats by viewModel.chats.collectAsState()
    val currentUserId by viewModel.currentUserId.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Верхняя панель с заголовком и возможными действиями
        ChatListTopBar()
        
        // Список чатов
        ChatsList(
            chats = chats,
            currentUserId = currentUserId,
            onChatClick = { viewModel.onChatClick(it) },
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Верхняя панель экрана чатов
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text("Чаты") },
        actions = {
            IconButton(onClick = { /* Действие для поиска */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Поиск"
                )
            }
            IconButton(onClick = { /* Действие для меню */ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Меню"
                )
            }
        },
        modifier = modifier
    )
}

/**
 * Список чатов
 */
@Composable
fun ChatsList(
    chats: List<ChatItem>,
    currentUserId: Long,
    onChatClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(chats) { chat ->
            ChatListItem(
                chat = chat,
                currentUserId = currentUserId,
                onClick = { onChatClick(chat.id) }
            )
            Divider(
                modifier = Modifier.padding(start = 72.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
fun ChatListItem(chat: ChatItem, currentUserId: Long, onClick: () -> Unit) {

}
