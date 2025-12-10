package it.sdc.restserver.dto;

import java.util.List;

// Root Update Object
public record Update(
        Long updateId,
        Message message,
        Message editedMessage,
        Message channelPost,
        Message editedChannelPost,
        CallbackQuery callbackQuery,
        InlineQuery inlineQuery,
        ChosenInlineResult chosenInlineResult,
        ShippingQuery shippingQuery,
        PreCheckoutQuery preCheckoutQuery,
        Poll poll,
        PollAnswer pollAnswer,
        ChatMemberUpdated chatMember,
        ChatJoinRequest chatJoinRequest
) {

    // Message
    public record Message(
            Long messageId,
            Integer messageThreadId,
            User from,
            Chat senderChat,
            Long date,
            Chat chat,
            User forwardFrom,
            Chat forwardFromChat,
            Integer forwardFromMessageId,
            String forwardSignature,
            String forwardSenderName,
            Long forwardDate,
            Boolean isTopicMessage,
            Boolean isAutomaticForward,
            Message replyToMessage,
            User viaBot,
            Long editDate,
            Boolean hasProtectedContent,
            String mediaGroupId,
            String authorSignature,
            String text,
            List<MessageEntity> entities,
            Animation animation,
            Audio audio,
            Document document,
            List<PhotoSize> photo,
            Sticker sticker,
            Video video,
            VideoNote videoNote,
            Voice voice,
            String caption,
            List<MessageEntity> captionEntities,
            Boolean hasMediaSpoiler,
            Contact contact,
            Dice dice,
            Game game,
            Poll poll,
            Venue venue,
            Location location,
            List<User> newChatMembers,
            User leftChatMember,
            String newChatTitle,
            List<PhotoSize> newChatPhoto,
            Boolean deleteChatPhoto,
            Boolean groupChatCreated,
            Boolean supergroupChatCreated,
            Boolean channelChatCreated,
            MessageAutoDeleteTimerChanged messageAutoDeleteTimerChanged,
            Long migrateToChatId,
            Long migrateFromChatId,
            Message pinnedMessage,
            Invoice invoice,
            SuccessfulPayment successfulPayment,
            String connectedWebsite,
            WriteAccessAllowed writeAccessAllowed,
            PassportData passportData,
            ProximityAlertTriggered proximityAlertTriggered,
            ForumTopicCreated forumTopicCreated,
            ForumTopicEdited forumTopicEdited,
            VideoChatScheduled videoChatScheduled,
            VideoChatEnded videoChatEnded,
            VideoChatParticipantsInvited videoChatParticipantsInvited,
            WebAppData webAppData,
            InlineKeyboardMarkup replyMarkup
    ) {
    }

    // Callback Query (Inline Keyboard Button Press)
    public record CallbackQuery(
            String id,
            User from,
            Message message,
            String inlineMessageId,
            String chatInstance,
            String data,
            String gameShortName
    ) {
    }

    // User
    public record User(
            Long id,
            Boolean isBot,
            String firstName,
            String lastName,
            String username,
            String languageCode,
            Boolean isPremium,
            Boolean addedToAttachmentMenu,
            Boolean canJoinGroups,
            Boolean canReadAllGroupMessages,
            Boolean supportsInlineQueries
    ) {
    }

    // Chat
    public record Chat(
            Long id,
            String type, // "private", "group", "supergroup", "channel"
            String title,
            String username,
            String firstName,
            String lastName,
            Boolean isForum,
            ChatPhoto photo,
            List<String> activeUsernames,
            String emojiStatusCustomEmojiId,
            String bio,
            Boolean hasPrivateForwards,
            Boolean hasRestrictedVoiceAndVideoMessages,
            Boolean joinToSendMessages,
            Boolean joinByRequest,
            String description,
            String inviteLink,
            Message pinnedMessage,
            ChatPermissions permissions,
            Integer slowModeDelay,
            Integer messageAutoDeleteTime,
            Boolean hasAggressiveAntiSpamEnabled,
            Boolean hasHiddenMembers,
            Boolean hasProtectedContent,
            String stickerSetName,
            Boolean canSetStickerSet,
            Long linkedChatId,
            ChatLocation location
    ) {
    }

    // Message Entity (for formatted text)
    public record MessageEntity(
            String type, // "mention", "hashtag", "cashtag", "bot_command", "url", "email", etc.
            Integer offset,
            Integer length,
            String url,
            User user,
            String language,
            String customEmojiId
    ) {
    }

    // Inline Keyboard
    public record InlineKeyboardMarkup(
            List<List<InlineKeyboardButton>> inlineKeyboard
    ) {
    }

    public record InlineKeyboardButton(
            String text,
            String url,
            String callbackData,
            WebAppInfo webApp,
            LoginUrl loginUrl,
            String switchInlineQuery,
            String switchInlineQueryCurrentChat,
            Boolean pay
    ) {
    }

    // Reply Keyboard
    public record ReplyKeyboardMarkup(
            List<List<KeyboardButton>> keyboard,
            Boolean isPersistent,
            Boolean resizeKeyboard,
            Boolean oneTimeKeyboard,
            String inputFieldPlaceholder,
            Boolean selective
    ) {
    }

    public record KeyboardButton(
            String text,
            KeyboardButtonRequestUser requestUser,
            KeyboardButtonRequestChat requestChat,
            Boolean requestContact,
            Boolean requestLocation,
            KeyboardButtonPollType requestPoll,
            WebAppInfo webApp
    ) {
    }

    // Media Types
    public record PhotoSize(
            String fileId,
            String fileUniqueId,
            Integer width,
            Integer height,
            Integer fileSize
    ) {
    }

    public record Audio(
            String fileId,
            String fileUniqueId,
            Integer duration,
            String performer,
            String title,
            String fileName,
            String mimeType,
            Integer fileSize,
            PhotoSize thumbnail
    ) {
    }

    public record Document(
            String fileId,
            String fileUniqueId,
            PhotoSize thumbnail,
            String fileName,
            String mimeType,
            Integer fileSize
    ) {
    }

    public record Video(
            String fileId,
            String fileUniqueId,
            Integer width,
            Integer height,
            Integer duration,
            PhotoSize thumbnail,
            String fileName,
            String mimeType,
            Integer fileSize
    ) {
    }

    public record Voice(
            String fileId,
            String fileUniqueId,
            Integer duration,
            String mimeType,
            Integer fileSize
    ) {
    }

    public record VideoNote(
            String fileId,
            String fileUniqueId,
            Integer length,
            Integer duration,
            PhotoSize thumbnail,
            Integer fileSize
    ) {
    }

    public record Animation(
            String fileId,
            String fileUniqueId,
            Integer width,
            Integer height,
            Integer duration,
            PhotoSize thumbnail,
            String fileName,
            String mimeType,
            Integer fileSize
    ) {
    }

    public record Sticker(
            String fileId,
            String fileUniqueId,
            String type, // "regular", "mask", "custom_emoji"
            Integer width,
            Integer height,
            Boolean isAnimated,
            Boolean isVideo,
            PhotoSize thumbnail,
            String emoji,
            String setName,
            Integer fileSize
    ) {
    }

    // Location & Contact
    public record Location(
            Double longitude,
            Double latitude,
            Double horizontalAccuracy,
            Integer livePeriod,
            Integer heading,
            Integer proximityAlertRadius
    ) {
    }

    public record Contact(
            String phoneNumber,
            String firstName,
            String lastName,
            Long userId,
            String vcard
    ) {
    }

    public record Venue(
            Location location,
            String title,
            String address,
            String foursquareId,
            String foursquareType,
            String googlePlaceId,
            String googlePlaceType
    ) {
    }

    // Poll
    public record Poll(
            String id,
            String question,
            List<PollOption> options,
            Integer totalVoterCount,
            Boolean isClosed,
            Boolean isAnonymous,
            String type, // "regular" or "quiz"
            Boolean allowsMultipleAnswers,
            Integer correctOptionId,
            String explanation,
            List<MessageEntity> explanationEntities,
            Integer openPeriod,
            Long closeDate
    ) {
    }

    public record PollOption(
            String text,
            Integer voterCount
    ) {
    }

    public record PollAnswer(
            String pollId,
            User user,
            List<Integer> optionIds
    ) {
    }

    // Inline Query
    public record InlineQuery(
            String id,
            User from,
            String query,
            String offset,
            String chatType,
            Location location
    ) {
    }

    public record ChosenInlineResult(
            String resultId,
            User from,
            Location location,
            String inlineMessageId,
            String query
    ) {
    }

    // Additional Support Records
    public record ChatPhoto(
            String smallFileId,
            String smallFileUniqueId,
            String bigFileId,
            String bigFileUniqueId
    ) {
    }

    public record ChatPermissions(
            Boolean canSendMessages,
            Boolean canSendAudios,
            Boolean canSendDocuments,
            Boolean canSendPhotos,
            Boolean canSendVideos,
            Boolean canSendVideoNotes,
            Boolean canSendVoiceNotes,
            Boolean canSendPolls,
            Boolean canSendOtherMessages,
            Boolean canAddWebPagePreviews,
            Boolean canChangeInfo,
            Boolean canInviteUsers,
            Boolean canPinMessages,
            Boolean canManageTopics
    ) {
    }

    public record ChatLocation(
            Location location,
            String address
    ) {
    }

    public record WebAppInfo(
            String url
    ) {
    }

    public record WebAppData(
            String data,
            String buttonText
    ) {
    }

    public record LoginUrl(
            String url,
            String forwardText,
            String botUsername,
            Boolean requestWriteAccess
    ) {
    }

    public record Dice(
            String emoji,
            Integer value
    ) {
    }

    public record Game(
            String title,
            String description,
            List<PhotoSize> photo,
            String text,
            List<MessageEntity> textEntities,
            Animation animation
    ) {
    }

    // Events
    public record MessageAutoDeleteTimerChanged(
            Integer messageAutoDeleteTime
    ) {
    }

    public record ForumTopicCreated(
            String name,
            Integer iconColor,
            String iconCustomEmojiId
    ) {
    }

    public record ForumTopicEdited(
            String name,
            String iconCustomEmojiId
    ) {
    }

    public record WriteAccessAllowed(
            String webAppName
    ) {
    }

    public record VideoChatScheduled(
            Long startDate
    ) {
    }

    public record VideoChatEnded(
            Integer duration
    ) {
    }

    public record VideoChatParticipantsInvited(
            List<User> users
    ) {
    }

    public record ProximityAlertTriggered(
            User traveler,
            User watcher,
            Integer distance
    ) {
    }

    // Chat Member Updates
    public record ChatMemberUpdated(
            Chat chat,
            User from,
            Long date,
            ChatMember oldChatMember,
            ChatMember newChatMember,
            InviteLink inviteLink
    ) {
    }

    public record ChatMember(
            String status, // "creator", "administrator", "member", "restricted", "left", "kicked"
            User user,
            Boolean isAnonymous,
            String customTitle,
            Boolean canBeEdited,
            Boolean canManageChat,
            Boolean canPostMessages,
            Boolean canEditMessages,
            Boolean canDeleteMessages,
            Boolean canManageVideoChats,
            Boolean canRestrictMembers,
            Boolean canPromoteMembers,
            Boolean canChangeInfo,
            Boolean canInviteUsers,
            Boolean canPinMessages,
            Boolean canManageTopics,
            Boolean isMember,
            Boolean canSendMessages,
            Boolean canSendAudios,
            Boolean canSendDocuments,
            Boolean canSendPhotos,
            Boolean canSendVideos,
            Boolean canSendVideoNotes,
            Boolean canSendVoiceNotes,
            Boolean canSendPolls,
            Boolean canSendOtherMessages,
            Boolean canAddWebPagePreviews,
            Long untilDate
    ) {
    }

    public record InviteLink(
            String inviteLink,
            User creator,
            Boolean createsJoinRequest,
            Boolean isPrimary,
            Boolean isRevoked,
            String name,
            Long expireDate,
            Integer memberLimit,
            Integer pendingJoinRequestCount
    ) {
    }

    public record ChatJoinRequest(
            Chat chat,
            User from,
            Long date,
            String bio,
            InviteLink inviteLink
    ) {
    }

    // Payments
    public record Invoice(
            String title,
            String description,
            String startParameter,
            String currency,
            Integer totalAmount
    ) {
    }

    public record SuccessfulPayment(
            String currency,
            Integer totalAmount,
            String invoicePayload,
            String shippingOptionId,
            OrderInfo orderInfo,
            String telegramPaymentChargeId,
            String providerPaymentChargeId
    ) {
    }

    public record ShippingQuery(
            String id,
            User from,
            String invoicePayload,
            ShippingAddress shippingAddress
    ) {
    }

    public record PreCheckoutQuery(
            String id,
            User from,
            String currency,
            Integer totalAmount,
            String invoicePayload,
            String shippingOptionId,
            OrderInfo orderInfo
    ) {
    }

    public record ShippingAddress(
            String countryCode,
            String state,
            String city,
            String streetLine1,
            String streetLine2,
            String postCode
    ) {
    }

    public record OrderInfo(
            String name,
            String phoneNumber,
            String email,
            ShippingAddress shippingAddress
    ) {
    }

    // Passport
    public record PassportData(
            List<PassportElement> data,
            EncryptedCredentials credentials
    ) {
    }

    public record PassportElement(
            String type,
            String data,
            String phoneNumber,
            String email,
            List<PassportFile> files,
            PassportFile frontSide,
            PassportFile reverseSide,
            PassportFile selfie,
            List<PassportFile> translation,
            String hash
    ) {
    }

    public record PassportFile(
            String fileId,
            String fileUniqueId,
            Integer fileSize,
            Long fileDate
    ) {
    }

    public record EncryptedCredentials(
            String data,
            String hash,
            String secret
    ) {
    }

    // Request Types for Keyboards
    public record KeyboardButtonRequestUser(
            Integer requestId,
            Boolean userIsBot,
            Boolean userIsPremium
    ) {
    }

    public record KeyboardButtonRequestChat(
            Integer requestId,
            Boolean chatIsChannel,
            Boolean chatIsForum,
            Boolean chatHasUsername,
            Boolean chatIsCreated,
            ChatAdministratorRights userAdministratorRights,
            ChatAdministratorRights botAdministratorRights,
            Boolean botIsMember
    ) {
    }

    public record KeyboardButtonPollType(
            String type
    ) {
    }

    public record ChatAdministratorRights(
            Boolean isAnonymous,
            Boolean canManageChat,
            Boolean canDeleteMessages,
            Boolean canManageVideoChats,
            Boolean canRestrictMembers,
            Boolean canPromoteMembers,
            Boolean canChangeInfo,
            Boolean canInviteUsers,
            Boolean canPostMessages,
            Boolean canEditMessages,
            Boolean canPinMessages,
            Boolean canManageTopics
    ) {
    }
}