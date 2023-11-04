package Test;
import Model.UserInfo;
import Controller.CardType;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CardTypeTest {

    @Test
    public void testCardType() {
        // Arrange
        UserInfo user1 = mock(UserInfo.class);
        when(user1.getCardNumber()).thenReturn("4111111111111111");

        UserInfo user2 = mock(UserInfo.class);
        when(user2.getCardNumber()).thenReturn("5111111111111111");

        UserInfo user3 = mock(UserInfo.class);
        when(user3.getCardNumber()).thenReturn("3111111111111111");

        ArrayList<UserInfo> users = new ArrayList<>(Arrays.asList(user1, user2, user3));

        CardType cardType = new CardType(); // Assuming default constructor

        // Act
        cardType.cardType(users);

        // Assert
        verify(user1, times(1)).getCardNumber();
        verify(user2, times(1)).getCardNumber();
        verify(user3, times(1)).getCardNumber();
    }
}
