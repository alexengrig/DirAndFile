<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Директории и файлы</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script src="/js/script.js"></script>
</head>
<body>
<h1>Директории и файлы</h1>
<div>
    <form method="post">
        <label>
            Новая директория:
            <input type="text" name="path">
        </label>
        <button type="submit">Добавить в список</button>
    </form>
</div>
<div>
    <div><h3>Список директорий и файлов</h3></div>
    <table>
        <thead>
        <tr>
            <th>Дата</th>
            <th>Базовая директория</th>
            <th>Директорий</th>
            <th>Файлов</th>
            <th>Суммарный размер файлов</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list dirs as dir>
        <tr>
            <td>${dir.date?string("dd.MM.yyyy HH:mm")}</td>
            <td>${dir.path}</td>
            <td>${dir.numDirs}</td>
            <td>${dir.numFiles}</td>
            <td>${dir.totalSize}</td>
            <td>
                <button class="modal-button-open" onclick="showModel('modal${dir_index}')">
                    Файлы
                </button>
                <div class="modal" id="modal${dir_index}">
                    <div class="modal-caption">${dir.path} ${dir.date?string("dd.MM.yyyy HH:mm")}</div>
                    <div class="modal-context">
                        <table>
                            <thead>
                            <tr>
                                <th>Файл</th>
                                <th>Размер</th>
                            </tr>
                            </thead>
                            <tbody>
                        <#list dir.files as file>
                        <tr>
                            <td>${file.name}</td>
                            <td>
                                <#if file.directory>
                                    &lt;DIR&gt;
                                <#else>
                                    ${file.size}
                                </#if>
                            </td>
                        </tr>
                        </#list>
                            </tbody>
                        </table>
                        <button class="modal-button-close" onclick="hideModel('modal${dir_index}')">
                            Закрыть
                        </button>
                    </div>

                </div>

            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>