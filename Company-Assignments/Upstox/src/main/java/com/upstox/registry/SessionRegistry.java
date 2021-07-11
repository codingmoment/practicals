package com.upstox.registry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Registry to store client subscriptions
 * 
 * @author N.Wani
 *
 */
public class SessionRegistry {
  private static final Map<String, Subscription> SUBSCRIPTIONS = new HashMap<>();

  public static void addSubscription(Subscription subscription) {
    SUBSCRIPTIONS.put(subscription.getSessionId(), subscription);
  }

  public static void removeSubscriptionForSession(String sessionId) {
    SUBSCRIPTIONS.remove(sessionId);
  }

  public static Collection<Subscription> getSubscriptions() {
    return SUBSCRIPTIONS.values();
  }
}
