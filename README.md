# <a name="AppName"></a> ShopList
Приложение, в котором использованы многие функции RecyclerView и переиспользуемость Fragment**

## Content
- [Особенности](#features)
- [Используемые технологии и библиотеки](#stack)
- [Скриншоты](#screenshots)
- [Главный экран](#main)
- [Добавление нового продукта](#adding)
- [Смена ориентации и редактирование товара](#editing)
- [Удаление товара](#deleting)

## <a name="description"></a> Описание
Приложение является списком покупок, в который можно добавлять новые продукты, у которых есть название и количество, редактировать их и удалять

## <a name="stack"></a> Используемые технологии и библиотеки
При разработке приложения использовались:
- Библиотека Room для базы данных
- Dagger 2 для dependency injection
- Recycler View для динамического изменения списка
- Паттерн MVVM

## <a name="screenshots"></a> Screenshots
### <a name="main"></a> Главный экран
*На главном экране находится список уже добавленных продуктов, которые подгружаются из БД*

<img src="https://github.com/user-attachments/assets/ae0689b9-3971-4e91-812e-9bbb56a0a204" width="512">

### <a name="adding"></a> Добавление нового продукта
*При нажатии на "+" осуществляется перед на фрагмент добавления товара*

<img src="https://github.com/user-attachments/assets/f662df29-1efd-4e8f-a188-90a300c2a031" width="512">

*Добавленный товар помещается в конец списка с демонстрацией Toast "Success"*

<img src="https://github.com/user-attachments/assets/d7585954-d5bf-4559-8ca5-e598d74661fb" width="512">


### <a name="editing"></a> Смена ориентации и редактирование товара
*При смене ориентации экран делится пополам и форма добавления/редактирования товара показывается справа от списка продуктов*

<img src="https://github.com/user-attachments/assets/7ed7eab9-ad3f-4ccc-b5bd-1479ae2d0a0d" width="512">

*Редактирование товара в горизонтальной ориентации*
  
<img src="https://github.com/user-attachments/assets/a424db7a-ea36-4970-b5e4-b09f327e8f59" width="512">

*После успешного редактирования также появляется сообщение "Success"*

<img src="https://github.com/user-attachments/assets/13698845-780d-44f5-8f4f-ade056ce41d2" width="512">


### <a name="deleting"></a> Удаление товара
*Удаление товара происходит свайпом*

<img src="https://github.com/user-attachments/assets/3c5ab092-6974-4dff-b19c-7ea81084ad6c" width="512">
<img src="https://github.com/user-attachments/assets/4d22ae67-6e0f-4658-9e08-c3e695907176" width="512">
