package org.gwtbootstrap3.extras.tagsinput.client.ui.base;

import org.gwtbootstrap3.extras.tagsinput.client.event.BeforeItemAddEvent;
import org.gwtbootstrap3.extras.tagsinput.client.event.HasBeforeItemAddHandlers;
import org.gwtbootstrap3.extras.tagsinput.client.event.HasBeforeItemRemoveHandlers;
import org.gwtbootstrap3.extras.tagsinput.client.event.HasItemAddedHandlers;
import org.gwtbootstrap3.extras.tagsinput.client.event.HasItemAddedOnInitHandlers;
import org.gwtbootstrap3.extras.tagsinput.client.event.HasItemRemovedHandlers;
import org.gwtbootstrap3.extras.tagsinput.client.event.ItemAddedOnInitEvent;

/**
 * Convenience interface used to implement all tagsinput handlers at once.
 *
 * @author Marko Nikolić <marko.nikolic@iten.rs>
 */
public interface HasAllTagsInputEvents<T>
    extends HasItemAddedOnInitHandlers<T>, HasBeforeItemAddHandlers<T>, HasItemAddedHandlers<T>, HasBeforeItemRemoveHandlers<T>,
                HasItemRemovedHandlers<T> {

  /**
   * The {@link ItemAddedOnInitEvent} name
   */
  String ITEM_ADDED_ON_INIT_EVENT = "itemAddedOnInit";

  /**
   * The {@link BeforeItemAddEvent} name
   */
  String BEFORE_ITEM_ADD_EVENT = "beforeItemAdd";

  /**
   * The {@link ItemAddedEvent} name
   */
  String ITEM_ADDED_EVENT = "itemAdded";

  /**
   * The {@link BeforeItemRemoveEvent} name
   */
  String BEFORE_ITEM_REMOVE_EVENT = "beforeItemRemove";

  /**
   * The {@link ItemRemovedEvent} name
   */
  String ITEM_REMOVED_EVENT = "itemRemoved";

  /**
   * The {@link ItemChangedEvent} name
   */
  String ITEM_CHANGED_EVENT = "change";
}
