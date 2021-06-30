# RecyclerViewTipsAndTricks

Репозиторий к докладу "Лучшие практики RecyclerView"
Видео: https://www.youtube.com/watch?v=o8rzzQPOo2U
Слайды: https://speakerdeck.com/elvisfromsouth/luchshiie-praktiki-recyclerview

## Содержание
Каждый шаг - отдельная ветка в репозитории

- [00-init](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/00-init) // Добавление основных сущностей (Модели, Активити, Разметки);
- [01-fingerprint](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/01-fingerprint) // Создание дополнительной сущности-посредника `Fingerprint`, на которую будет делегировано манипулирование `ViewHolder` для каждого отдельного элемента списка;
- [02-bind_optimization](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/02-bind_optimization) // "Классический" пример оптимизации в вызове `onBindViewHolder(...)`;
- [03-decorations](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/03-decorations) // Пример использования `ItemDecorations` в вопросе выставления отступов у элементов;
- [04-diffutil](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/04-diffutil) // Добавление `DiffUtil`, инструмента который высчитывает изменения в списке;
- [05-listadapter](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/05-listadapter) // Добавление `ListAdapter`, адаптера который выполняет расчет `DiffUtil` в другом потоке;
- [06-payloads](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/06-payloads) // Добавление расчета Payload для `DiffUtils`, для изменения части данных внутри элемента списка;
- [07-custom_animations](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/07-custom_animations) // Добавление кастомных анимаций для элементов списка;
- [08-swipe_to_delete](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/08-swipe_to_delete) // Испльзование `ItemTouchHelper` для реализации Swipe-To-Delete;
- [09-concat_adapter](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/09-concat_adapter) // Использование `ConcatAdapter`;
- [10-horizontal_items](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/10-horizontal_items) // Добавление горизонтального `RecyclerView` в демо;
- [11-horizontal_fixed_width](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/11-horizontal_fixed_width) // Способ задать фиксированный размер элемента внутри горизинтального RecyclerView;
- [12-horizontal_save_state](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/12-horizontal_save_state) // Восстановления состояния вложенного горизонтального `RecyclerView`;
- [13-horizontal_items_optimisations](https://github.com/elvisfromsouth/RecyclerViewTipsAndTricks/tree/13-horizontal_items_optimisations) // Оптимизации для вложенных горизонтальных `RecyclerView`;
